package main

import (
	"fmt"
	"fyne.io/fyne/v2"
	"fyne.io/fyne/v2/app"
	"fyne.io/fyne/v2/canvas"
	"image"
	"image/color"
	"os"
	"strconv"
)

func Pic(dx, dy int) [][]uint8 {
	var counter uint8
	a := make([][]uint8, dy)
	//fmt.Printf("%v\n", a)
	for i := 0; i < dy; i++ {
		a[i] = make([]uint8, dx)
		for j := 0; j < dx; j++ {
			//if counter > 9 {
			//	counter = 0
			//}
			if counter == uint8((1<<8)-1) {
				counter = 0
			}
			a[i][j] = counter
			counter++
		}
	}
	return a
}

func main() {
	dx, _ := strconv.Atoi(os.Args[1])
	dy, _ := strconv.Atoi(os.Args[2])
	x := Pic(dx, dy)
	//printPic(x)
	//pic.Show(Pic)

	// Show with fyne
	a := app.New()
	w := a.NewWindow("Images")

	img := canvas.NewImageFromImage(generateImage(x))
	w.SetContent(img)
	w.Resize(fyne.NewSize(float32(dx), float32(dy)))

	w.ShowAndRun()
}

func generateImage(x [][]uint8) image.Image {
	rect := image.Rect(0, 0, len(x), len(x[0]))
	rgba := image.NewRGBA(rect)

	for i := 0; i < len(x); i++ {
		for j := 0; j < len(x[i]); j++ {
			pColor := color.RGBA{
				R: 0,
				G: x[i][j],
				B: 0,
				A: 100,
			}
			rgba.Set(i, j, pColor)
		}
	}

	return rgba
}

func printPic(p [][]uint8) {
	for i, v := range p {
		fmt.Printf("[%d] %v\n", i, v)
	}
}