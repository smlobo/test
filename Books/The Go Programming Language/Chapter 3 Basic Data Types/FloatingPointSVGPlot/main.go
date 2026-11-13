package main

import (
	"fmt"
	"math"
)

const (
	width, height = 1200, 640
	cells = 100
	xyrange = 30.0
	xyscale = width / 2 / xyrange
	zscale = height * 0.4
	angle = math.Pi / 6
)

var sin30, cos30 = math.Sin(angle), math.Cos(angle)
var zmax, zmin = math.Inf(-1), math.Inf(+1)

func main() {
	fmt.Printf("<svg xmlns='http://www.w3.org/2000/svg' " +
		"style='stroke: #444444; fill: #ffffff; stroke-width: 0.7' " +
		"width='%d' height='%d'>\n", width, height)
	for i := 0; i < cells; i++ {
		for j := 0; j < cells; j++ {
			ax, ay, az := corner(i+1, j)
			bx, by, bz := corner(i, j)
			cx, cy, cz := corner(i, j+1)
			dx, dy, dz := corner(i+1, j+1)

			// Average color based height (az, bz, cz, dz)
			z := (az+bz+cz+dz)/4.0
			darkness := int(math.Abs(z * 255.0 * 4))
			if darkness > 0xff {
				darkness = 0xff
			}
			red, green, blue := 0x0, 0xff, 0x0
			if z > 0.0 {
				red = darkness
			} else {
				blue = darkness
			}
			green -= darkness

			fmt.Printf("<polygon stroke='#%02x%02x%02x' points='%g,%g %g,%g %g,%g %g,%g'/>\n",
				red, green, blue, ax, ay, bx, by, cx, cy, dx, dy)
		}
	}
	fmt.Println("</svg>")
	//fmt.Printf("zmax: %4.3f, zmin: %4.3f\n", zmax, zmin)
}

func corner(i, j int) (float64, float64, float64) {
	// Find point (x,y) at corner of cell (i,j)
	x := xyrange * (float64(i)/cells - 0.5)
	y := xyrange * (float64(j)/cells - 0.5)

	// Compute surface height z
	z := f(x, y)
	if z > zmax {
		zmax = z
	} else if z < zmin {
		zmin = z
	}

	// Project (x,y,z) isometrically onto a 2D SVG canvas
	sx := width/2 + (x-y)*cos30*xyscale
	sy := height/2 + (x+y)*sin30*xyscale - z*zscale

	return sx, sy, z
}

func f(x, y float64) float64 {
	r := math.Hypot(x, y)
	return math.Sin(r) / r
}
