package main

// import fyne
import (
	"fyne.io/fyne/v2"
	"fyne.io/fyne/v2/app"
	"fyne.io/fyne/v2/canvas"
	"fyne.io/fyne/v2/container"
	"image/color"
)

func main() {
	// New App
	a := app.New()
	// New title and window
	w := a.NewWindow("Layout = My FAV")
	// resize
	w.Resize(fyne.NewSize(400, 400))
	//Rectangle
	Red_RECT := canvas.NewRectangle(color.RGBA{R: 255, G: 0, B: 0, A: 255})
	// Size of Rectangle very important
	Red_RECT.Resize(fyne.NewSize(50, 50)) // 50x50 pixels
	// Position of rectangle on screen
	//Red_RECT.Move(fyne.NewPos(0, 0))
	// (0,0) x axis & y axis is zero
	// I need rectangle at bottom
	// our canvas size is 400x400
	// and rectangle size is 50x50
	// 400-50 = 350 // 350 is bottom for our rectangle
	//Red_RECT.Move(fyne.NewPos(350, 350))
	//I need my rectangle in center
	// so our calculation will be
	// 400-50= 350
	// now for center divide by 2
	// 350/2 = 175
	// 175X175
	Red_RECT.Move(fyne.NewPos(175, 175))
	// Let setup content
	// we are going to use container without layout
	Blue_RECT := canvas.NewRectangle(color.RGBA{R: 0, G: 0, B: 255, A: 255})
	Blue_RECT.Resize(fyne.NewSize(100, 100)) // 50x50 pixels
	Blue_RECT.Move(fyne.NewPos(25, 175))
	//w.SetContent(
	//	container.NewWithoutLayout(
	//		Red_RECT,
	//		Blue_RECT,
	//	),
	//)

	// Let setup content
	// we are going to use container without layout
	//w.SetContent(
	//	container.NewWithoutLayout(
	//		Blue_RECT,
	//	),
	//)

	// line
	greenLine := canvas.Line{
		Position1: fyne.Position{
			X: 10,
			Y: 10,
		},
		Position2: fyne.Position{
			X: 200,
			Y: 10,
		},
		Hidden: false,
		StrokeColor: color.RGBA{
			R: 0,
			G: 255,
			B: 0,
			A: 255,
		},
		StrokeWidth: 3,
	}
	c2 := container.NewWithoutLayout()
	w.SetContent(c2)
	c2.Add(Red_RECT)
	c2.Add(Blue_RECT)
	c2.Add(&greenLine)

	//show and run
	w.ShowAndRun()
}
