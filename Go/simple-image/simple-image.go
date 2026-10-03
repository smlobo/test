package main

import (
	"fyne.io/fyne/v2"
	"fyne.io/fyne/v2/app"
	"fyne.io/fyne/v2/canvas"
	"golang.org/x/image/colornames"
	"image"
)

func main() {
	a := app.New()
	w := a.NewWindow("Images")

	img := canvas.NewImageFromImage(generateImage())
	w.SetContent(img)
	w.Resize(fyne.NewSize(640, 480))

	w.ShowAndRun()
}

func generateImage() image.Image {
	//blue := color.RGBA{B: 255, A: 255}
	//return &image.Uniform{C: blue}
	//return image.Rect(0, 0, 640, 480)
	rect := image.Rect(0, 0, 640, 480)
	rgba := image.NewRGBA(rect)
	for i := 0; i < 640; i++ {
		for j := 0; j < 480; j++ {
			rgba.Set(i, j, colornames.Green)
		}
	}
	return rgba
}
