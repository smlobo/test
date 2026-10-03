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

	// Read all records
	var products []Product
	result := db.Find(&products)
	fmt.Printf("Found: %d products\n", result.RowsAffected)
	for i, product := range products {
		fmt.Printf("[%d] %v\n", i, product)
	}
}
