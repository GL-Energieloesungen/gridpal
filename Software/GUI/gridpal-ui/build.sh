#!/bin/sh

ng build --aot --prod
version=$(grep version package.json | awk '{print $2}' | tr -d \",)
rm *.zip
zip -r gridpal.ui-$version.zip dist
