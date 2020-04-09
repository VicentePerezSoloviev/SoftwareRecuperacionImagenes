# Software de Recuperacion de Imagenes
The aim of this software is to perform images management. Databases with images can be created to work with them. A comparison among the iamges of a database can be done, comparing the form and colors of the objects. OpenCV is used to manage histograms and compare them. Java implementation.

The main functionalities are:
- Create a database
- Delete a database
- Edit a database
- View the database images
- Add a new image to the database
- Perform a ranking of similarity among the images in the database. The ranking can be performed condiring form in the image or colors. All the ranking are calculated using the histograms of the images, using OpenCV library.
- Help can be consulted in every windows of the system

# To use it:

Requirements:
- Java 64 bits https://www.java.com/es/download/manual.jsp

To install the app:
1. Copy the file 'directoriRaiz' to C://. This file specifies the location of the directory
2. Copy the directory 'DirectorioRaiz' to C://. THis directory contains the databases
2. Init the app

To add the first database
1. Load the app
2. Create a new database with name and description
3. Add images to the database
4. Calculate descriptors and work with it!

![Image of the main screen from which functionalities can be used](https://github.com/VicentePerezSoloviev/SoftwareRecuperacionImagenes/image.png)


