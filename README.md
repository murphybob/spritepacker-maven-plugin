Spritepacker Maven Plugin
=========================

This project is a maven plugin to take a set of input images and combine them into a PNG spritesheet. It can also produce a JSON(P) file alongside that with descriptive information about the spritesheet layout. This can be plugged into other tools for a more automated approach to using a spritesheet in your project.  Typical usage would be as part of a web project build alongside [lesscss-maven-plugin with custom JS support](http://github.com/murphybob/lesscss-maven-plugin).

Install
=======

* Clone this repository and run mvn install

Usage
=====

Include the plugin in your maven project, and use the pom.xml configuration directives to tell it what images you want to combine and where you would like it to combine them to.

	<plugin>
		<groupId>com.murphybob</groupId>
		<artifactId>spritepacker-maven-plugin</artifactId>
		<version>0.9</version>
		<configuration>
			<sourceDirectory>${project.basedir}/src/images/sprites/</sourceDirectory>
			<includes>
				<include>*.png</include>
			</includes>
			<output>${project.build.directory}/images/assets-sprite.png</output>
			<json>${project.build.directory}/images/assets-sprite.json</json>
			<padding>10</padding>
			<executions>
				<execution>
					<goals>
						<goal>compile</goal>
					</goals>
				</execution>
			</executions>
		</configuration>
	</plugin>

Configuration
=============

<dl>

	<dt>sourceDirectory</dt>
	<dd>The directory where your source images reside.  This will be scanned recursively and files included based on include/exclude rules.</dd>
	
	<dt>includes</dt>
	<dd>Expression of which files to include.  See [http://plexus.codehaus.org/plexus-utils/apidocs/org/codehaus/plexus/util/DirectoryScanner.html] for more details.</dt>
	
	<dt>excludes</dt>
	<dd>Expression of which files to exclude.  See [http://plexus.codehaus.org/plexus-utils/apidocs/org/codehaus/plexus/util/DirectoryScanner.html] for more details.</dt>
	
	<dt>output</dt>
	<dd>File to write PNG spritesheet to.</dd>
	
	<dt>json</dt>
	<dd>File to write JSON(P) spritesheet meta data to. See below for structure.</dd>
	
	<dt>jsonpVar</dt>
	<dd>If set this is used as a padding variable to make the JSON file into a JSONP file which may be more useful depending on your application. e.g.<br>
		*{ image: {...} }*<br>
		becomes<br>
		*jsonpVar = { image: {...} }*
	</dd>
	
	<dt>padding</dt>
	<dd>Padding in pixels to be added around each image and the edges of the spritesheet.  Useful if you are having problems with images bleeding into each other due to users zooming, sub-pixel rendering, etc...</dd>
   
</dl>

JSON
====

The JSON is formatted as in the example below.  At the top level are keys which are derived from the file name of the image, with the extension stripped.  For that reason, if you wish to use this data you must ensure these filenames are unique throughout the entire list of images.

At the next level are the width (*w*), height (*h*), negative x location (*x*), negative y location (*y*), and both x and y in one for convenience (*xy*) as strings with "px" appended for use in a stylesheet.

At that level is also *n* which contains the same keys (apart from *xy*) with the same values as pure integers. 

	{
		"example" : {
			"w" : "128px",
			"n" : {
				"w" : 128,
				"h" : 128,
				"y" : 10,
				"x" : 398
			},
			"h" : "128px",
			"y" : "-10px",
			"xy" : "-398px -10px",
			"x" : "-398px"
		},
		"anotherImage" : {
			"w" : "378px",
			"n" : {
				"w" : 378,
				"h" : 378,
				"y" : 10,
				"x" : 10
			},
			"h" : "378px",
			"y" : "-10px",
			"xy" : "-10px -10px",
			"x" : "-10px"
		}
	}

Notes
=====

This was built for use with the official lesscss-maven-plugin with the idea being this plugin creates a spritesheet and writes the data, and that plugin loads the data and makes it available within the less files.  That's not possible in the standard lesscss-maven-plugin because it can't read custom JS files however lesscss-java does have the ability to do so, meaning it was quite simple to add to lesscss-maven-plugin.  I have a forked version available here [lesscss-maven-plugin with customJs parameter](https://github.com/murphybob/lesscss-maven-plugin) which will allow this.

If you are using that then add *jsonpVar* in the Spritepacker configuration in your pom.xml, and add the output jsonp file as a *customJsFile* in the lesscss-maven-plugin pom.xml and you will be able to reference sprites in your less files like this:

	.sprite(@sprite){
	  background-image: url(../images/assets-sprite.png);
	  width: ~`Sprites[@{sprite}].w`;
	  height: ~`Sprites[@{sprite}].h`;
	  background-position: ~`Sprites[@{sprite}].xy`;
	}
	
	#demo1 {
	  .sprite("bob");
	  border: 1px solid red;
	}
	
where *Sprites* is the value of jsonpVar.

It's also worth noting that this uses Java libraries for creating the spritesheet, it can almost certainly be made smaller by adding your favourite PNG optimiser (optipng, deflopt, advancepng, etc) downstream in the build process. 

License
=======

See [LICENSE](https://github.com/murphybob/spritepacker/blob/master/LICENSE) file.
