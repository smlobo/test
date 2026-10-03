package main

import (
	"fyne.io/fyne/v2"
	"fyne.io/fyne/v2/app"
	"fyne.io/fyne/v2/canvas"
	"image"
	"image/color"
)

type Image struct{}

func (i Image) ColorModel() color.Model {
	return color.RGBAModel
}

func (i Image) At(x, y int) color.Color {
	return color.RGBA{
		R: uint8(x),
		G: uint8(y),
		B: 0,
		A: 255,
	}
}

func (i Image) Bounds() image.Rectangle {
	return image.Rectangle{
		Min: image.Point{},
		Max: image.Point{X: 100, Y: 100},
	}
}

func main() {
	m := Image{}
	//pic.ShowImage(m)

	// Show with fyne
	a := app.New()
	w := a.NewWindow("Images")

	img := canvas.NewImageFromImage(m)
	w.SetContent(img)
	w.Resize(fyne.NewSize(float32(m.Bounds().Max.X), float32(m.Bounds().Max.Y)))

	w.ShowAndRun()
}

