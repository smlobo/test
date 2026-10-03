#include <algorithm>
#include <chrono>
#include <iostream>
#include <mutex>
#include <stdexcept>

class TokenBucket {
public:
    TokenBucket(double capacity, double tokens_per_second)
        : capacity_{capacity},
          tokens_{capacity},
          refill_rate_{tokens_per_second},
          last_refill_{Clock::now()}
    {
        if (capacity <= 0.0 || tokens_per_second <= 0.0) {
            throw std::invalid_argument(
                "capacity and refill rate must be positive"
            );
        }
    }

    bool try_consume(double amount = 1.0)
    {
        if (amount <= 0.0) {
            throw std::invalid_argument(
                "token amount must be positive"
            );
        }

        std::lock_guard<std::mutex> lock{mutex_};
        refill();

        if (tokens_ < amount) {
            return false;
        }

        tokens_ -= amount;
        return true;
    }

private:
    using Clock = std::chrono::steady_clock;

    void refill()
    {
        const auto now = Clock::now();
        const std::chrono::duration<double> elapsed =
            now - last_refill_;

        tokens_ = std::min(
            capacity_,
            tokens_ + elapsed.count() * refill_rate_
        );

        last_refill_ = now;
    }

    const double capacity_;
    double tokens_;
    const double refill_rate_;
    Clock::time_point last_refill_;
    std::mutex mutex_;
};

int main()
{
    // Allow bursts of up to 10 operations and refill 5 tokens per second.
    TokenBucket bucket{10.0, 5.0};

    for (int request = 0; request < 15; ++request) {
        if (bucket.try_consume()) {
            std::cout << "Request allowed\n";
        } else {
            std::cout << "Request rejected\n";
        }
    }
}
