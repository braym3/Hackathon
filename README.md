Coverage: 82% Instructions 79% Branches
# IMS: Inventory Management System

IMS is a system for accessing, creating, and manipulating the inventory of a shop. It has the ability to create orders, items, and customers, and to calcuate the cost of their order. 

## Getting Started

Firstly, you need to download the code from the remote repository. Then create a schema and tables based off the schema provided in the code. Afterwards all you need to do is run the IMS with dependencies jar file in the command line.
Running a Jar file is a simple process of typing "java -jar ims-0.0.1-jar-with-dependencies" into your command line in the correct folder.

### Prerequisites

To run this software you will need an sql server running locally and a version of Java installed

```
Java 17 will need to be installed
MySQL is the SQL language of choice
Maven is also required if tests are wished to be run
A suitable IDE
```

### Installing

To install this code into a dev enviroment you will need a Java IDE which can support Maven projects

After that, the first step is to import the project.
```
While importing varies per language, typical workflow is:
File -> Import
Then select the downloaded code
```

After the code is imported, you can either run it from the command line or from the IDE. Typically the IDE is easier but CMD line is more customisable
```
The typical workflow involves press F5 to run the Runner code
```

If you have not installed the SQL, the code will have unforeseen errors in.

## Running the tests

To run the automated tests for this system

```
mvn test
```

Is the command of choice, this will output a website to more clearly view the results of the test aswell as outputting to the console.
### Unit Tests 

Unit tests are small blocks of code tested independently
```
@Test
	public void testCreate() {
		final Order created = new Order(2L, "order_name", 1L);
		assertEquals(null, DAO.create(created));
	}
```
Above is a typical unit test.

### Integration Tests 

Integration testing was performed by following a testing specification and methodically trying everykind of bad input.

Integration testing was crucial in catching stray logic errors.


## Deployment

If you were going to deploy this on a live system, you would need to expand access to the CLI or convert to a GUI as it's currently clunky.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Lauren Darlaston** - *Follow up work* - [AelinaXY](https://github.com/AelinaXY/)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Jordan Harrison for help