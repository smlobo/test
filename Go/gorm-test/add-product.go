package main

import (
	"fmt"
	"gorm.io/driver/sqlite"
	"gorm.io/gorm"
)

func main() {
	db, err := gorm.Open(sqlite.Open("test.db"), &gorm.Config{})
	if err != nil {
		panic("failed to connect database")
	}

	for i := 0; i < 5; i++ {
		code := fmt.Sprintf("XX%d", i)
		price := uint(i*2 + 10)
		db.Create(&Product{
			Code:  code,
			Price: price,
		})
	}
}
