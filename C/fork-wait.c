#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, const char *argv[]) {
    printf("Parent process: %6d\n", getpid());

    // Child1 process
    pid_t child1_pid = fork();
    if (child1_pid == 0) {
        printf("  Child1 process: %6d (parent: %6d)\n", getpid(), getppid());

        int child_sleep = 1;
        printf("  Child1 sleeping: %dsec\n", child_sleep);
        sleep(child_sleep);
        printf("  Child1 done: %6d (parent: %6d)\n", getpid(), getppid());

        exit(0);
    }

    // Child2 process
    pid_t child2_pid = fork();
    if (child2_pid == 0) {
        printf("  Child2 process: %6d (parent: %6d)\n", getpid(), getppid());

        int child_sleep = 3;
        printf("  Child2 sleeping: %dsec\n", child_sleep);
        sleep(child_sleep);
        printf("  Child2 done: %6d (parent: %6d)\n", getpid(), getppid());

        exit(0);
    }

    printf("Parent process: %d, child1: %d, child2: %d\n", getpid(), child1_pid, child2_pid);

    int parent_sleep = 0;
    printf("Parent sleeping: %dsec\n", parent_sleep);
    sleep(parent_sleep);
    printf("Parent sleep done\n");

    // Wait for child2 to finish, then child1
    int count = 0;
    int child2_done = 0;
    while (1) {
        int child_status;
        // pid_t wpid = waitpid(child_pid, &child_status, WNOHANG);
        // pid_t wpid = waitpid(child_pid, &child_status, 0);
        pid_t wpid = waitpid(-1, &child_status, 0);

        // Child1 done (after child2)
        if (wpid == child1_pid && child2_done) {
            // if (!WIFEXITED(child_status))
            //     printf("Child %d terminate abnormally\n", wpid);
            break;
        }
        // Child2 done
        else if (wpid == child2_pid) {
            // if (!WIFEXITED(child_status))
            //     printf("Child %d terminate abnormally\n", wpid);
            child2_done = 1;
        }


        if (child2_done)
            printf("[%d] Parent: %d, waiting for child1: %d {waitpid ret: %d}\n",
                count++, getpid(), child1_pid, wpid);
        else
            printf("[%d] Parent: %d, waiting for child1/2: %d/%d {waitpid ret: %d}\n",
                count++, getpid(), child1_pid, child2_pid, wpid);
        usleep(500000);
    }

    printf("Parent process: %d, Waiting for child1/2 done: %d/%d\n", getpid(), child1_pid,
        child2_pid);

    return 0;
}
