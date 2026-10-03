package main

import (
	"image"
	"image/color"
	"image/draw"
	"time"

	"github.com/faiface/gui"
	"github.com/faiface/gui/win"
	"github.com/faiface/mainthread"
)

func ColorPicker(env gui.Env, r image.Rectangle, clr color.Color) {
	env.Draw() <- func(drw draw.Image) image.Rectangle {
		draw.Draw(drw, r, &image.Uniform{clr}, r.Min, draw.Src)
		return r
	}

	close(env.Draw())
}

var col color.Color

func run() {
	w, err := win.New(win.Title("Paint"), win.Size(800, 600))
	if err != nil {
		panic(err)
	}

	//mux, _ := gui.NewMux(w)

	for i, clr := range []color.Color{
		color.RGBA{255, 0, 0, 255},
		color.RGBA{255, 255, 0, 255},
		color.RGBA{0, 255, 0, 255},
		color.RGBA{0, 255, 255, 255},
		color.RGBA{0, 0, 255, 255},
		color.RGBA{255, 0, 255, 255},
		color.RGBA{255, 255, 255, 255},
		color.RGBA{0, 0, 0, 255},
	} {
		go ColorPicker(w, image.Rect(750, i*75, 800, (i+1)*75), clr)
	}

	col = color.RGBA{255, 0, 0, 255} // Red
	r1 := image.Rect(500, 50, 800, 200)
	//draw.Draw(w.Draw(), r1, &image.Uniform{col}, r1.Min, draw.Src)
	go ColorPicker(w, r1, col)
	time.Sleep(10 * time.Second)
}

func main() {
	mainthread.Run(run)
}
