# Documentation Guide
[Back to Home](/README.md) | [Back to General Doc](/docs/readme.md) 

---
## Documents
- Markdown format file
- [Markdown Guide](
https://confluence.atlassian.com/bitbucketserver/markdown-syntax-guide-776639995.html)

## Diagrams
- Install VS Code plugin PlantUML.
- Generate files with PlantUML sintaxis with wsd extension.
- Press Alt+D to Preview.
- Config Visual studio to include all class automatically:  

- Plant Editor with examples  
https://www.planttext.com/
- PlantUML from Typescript class   
https://tsuml-demo.firebaseapp.com/
- Generate png's
	```
	cd docs/diagrams
	java -jar plantuml.jar "**/*.wsd"
	```

## Typedoc  Auto Generator 
```	
sudo npm install --global typedoc
typedoc -out docs                   or
typedoc -out docs --hideGenerator   or
typedoc -out docs --hideGenerator --theme minimal

```
## Markdown conversor

### [Pandoc Guide](https://pandoc.org/MANUAL.html)

### Pandoc Installation
```
sudo apt-get install pandoc texlive graphviz
```

### Pandoc Run
```
pandoc --version
pandoc README.md -o README.pdf
```

---
[Go to Top](#markdown-header-documentation-guide-pagossucursalweb)  
[Back to Home](/README.md) | [Back to General Doc](/docs/readme.md)