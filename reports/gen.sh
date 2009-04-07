#!/bin/sh
cd `dirname $0`
pdflatex $01.tex
rm *.log *.out *.aux
