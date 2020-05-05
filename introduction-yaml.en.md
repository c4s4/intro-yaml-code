---
title:      Introduction to YAML
author:     Michel Casabianca
date:       2018-03-02
updated:    2018-03-02
categories: [articles]
tags:       [yaml, python, java, golang]
id:         introduction-yaml.en
email:      casa@sweetohm.net
lang:       en
toc:        true
---

This article is an introduction to YAML, a language to write structured data, as XML would for example, but in a more natural and less verbose way. We will see a description of the YAML syntax as well as examples in Java, Python and Go.

<!--more-->

Une [version en français est disponible ici](http://sweetohm.net/article/introduction-yaml.html).

A ZIP archive with source code for examples can be found at: [http://www.sweetohm.net/arc/introduction-yaml.zip](http://www.sweetohm.net/arc/introduction-yaml.zip).

What is YAML?
=============

The name YAML means **YAML Ain't Markup Language**. If this immediately distances XML, it does not tell us what YAML is. YAML is, [according to its specification] (http://yaml.org/spec/1.2/spec.html), a data serialization language designed to be human-readable and working well with modern programming languages for everyday tasks.

Specifically, we could note the list of ingredients for a breakfast as follows:

```yaml/1.yml```

This is a valid YAML file that represents a list of strings. To be convinced, we can write the following Python script that parses the file, whose name is passed on the command line, and displays the result:

```python/1.py```

This script will produce the following result:

```yaml/2.yml```

Which means that the result of this parsing is a Python list containing the appropriate strings! The parser is therefore able to render *natural* data structures of the language used for parsing.

Now let's write a countdown:

```yaml/3.yml```

We obtain the following result:

```yaml
[3, 2, 1, 0]
```

This is always a list, but the parser has recognized each element as an integer and not a simple string as in the previous example. The parser is therefore able to distinguish data types such as strings and integers, floating-point numbers, and dates. To do this, we use a natural syntax: *3* is recognized as an integer while *ascending* is not because the integer cannot be converted to an integer, a floating point number, or any other type recognized by YAML. To force YAML to interpret *3* as a string, you can surround it with quotation marks.

YAML can also recognize associative arrays, so we could note a breakfast order as follows:

```yaml/4.yml```

Which will be loaded in the following way:

```python
'chocolate breads': 30, 'croissants': 30, 'ham': 0, 'eggs': 0}
```

By combining the basic data types in YAML-recognized collections, almost any data structure can be represented. On the other hand, the textual representation of these data is very legible and almost natural.

It is also possible to perform the reverse operation, namely to serialize data structures in memory as text. In the following example, we write on standard output a Python `Dictionary`:

```python/2.py```

Which will produce the following output:

```yaml
difficulty: difficult
ingredients: [rice, vinegar, sugar, salt, tuna, salmon]
name: sushi
cooking time: 10
```

Basic syntax
============

After this brief introduction, here is a more exhaustive description of the YAML syntax.

Scalar
------

Scalars are the set of YAML types that are not collections (list or associative array). They can be represented by a list of Unicode characters. Here is a list of scalars recognized by YAML parsers:

### String of characters

Here is an example :

```yaml/5.yml```

Which is parsed in the following way:

```python
[u'String', '3', u'String on a single line',
  'Double quotation marks\t', 'Simple quotation marks\\t']
```

The result of this parsing leads us to the following comments:

- Accented characters are handled, and more generally, Unicode is managed.
- Line breaks are not taken into account in strings, they are managed as in HTML or XML, namely they are replaced by spaces.
- Double quotation marks handle escape characters, such as \t for tabulation for instance.
- Single quotes do not handle escape characters that are transcribed literally.
- The list of escape characters managed by YAML includes the classical values, but also many others that can be found [in the YAML specification](http://yaml.org/spec/current.html#id2517668).

On the other hand, it is possible to write Unicode characters using the following notations:

- `\xNN`: to write 8-bit Unicode characters, where `NN` is a hexadecimal number.
- `\uNNNN`: for 16-bit Unicode characters.
- `\UNNNNNNNN`: for 32-bit Unicode characters.

### Integers

Here are some examples:

```yaml/6.yml```

Which is parsed in the following way:

```python
{'octal': 12345, 'hexadecimal': 12345, 'canonical': 12345,
 'decimal': 12345, 'sexagesimal': 12345}
```

Thus most common notations of programming languages ​​(such as octal or hexadecimal) are handled. Note that all these notations will be recognized as identical by a YAML parser and consequently will be equivalent like key of an associative array for example.

### Floating point numbers

Let's see the different notations for these numbers:

```yaml/7.yml```

Which is parsed in:

```python
{'not a number': nan, 'sexagesimal': 1230.1500000000001,
 'exponential': 1230.1500000000001, 'fixed': 1230.1500000000001,
 'infinite negative': -inf, 'canonical': 1230.1500000000001}
```

Classical notations are handled as well as infinites and values ​​that are not numbers.

### Dates

YAML also recognizes dates:

```yaml/8.yml```

Which are parsed in the following way:

```python
{'date': datetime.date(2002, 12, 14),
 'iso8601': datetime.datetime (2001, 12, 15, 2, 59, 43, 100000),
 'canonical': datetime.datetime (2001, 12, 15, 2, 59, 43, 100000),
 'space': datetime.datetime (2001, 12, 15, 2, 59, 43, 100000)}
```

The types resulting from the parsing depend on the language and the parser, but correspond to natural types for the considered times.

### Miscellaneous

There are other scalars recognized by YAML parsers:

```yaml/9.yml```

Who will be parsed in:

```python
{'false bis': False, 'true ter': True, 'true bis': True,
 'false ter': False, 'null': None, 'false': False,
 'null bis': None, 'true': True}
```

Of course, the type of parsed values ​​depends on the language and other special values ​​can be recognized according to the languages ​​and parsers. For example, the Ruby parser recognizes symbols (denoted `: symbol` for instance) and parses them into Ruby symbols.

## Collections

There are two types of collections recognized by YAML: lists and associative arrays.

### Lists

These are ordered lists, and may contain several identical elements (as opposed to sets). The elements of a list are identified by a hyphen, as follows:

```yaml/10.yml```

The elements of the list are distinguished thanks to the indentation: the first element is identified so that its second line is recognized as part of the first element of the list. This syntax can be compared to that of Python, except that in YAML, **tab characters are strictly forbidden for indentation**. This last rule is important and causes many parsing errors. It is important to set its editor to prohibit tabs for indenting YAML files.

There is an alternative notation for lists, similar to that of Python or Ruby languages:

```yaml/11.yml```

This notation, called *in flow*, is more compact and sometimes allows to gain readability or compactness.

### Associative Tables

Called *Maps* or *Dictionaries* in some languages, they associate a value with a key:

```yaml/12.yml```

*Flow* notation is as follows:

```yaml/13.yml```

Which is parsed in the same way. This notation is identical to that of Python or Javascript and is similar to that used by Ruby. Note that Ruby 2 is also using this notation.

## Comments

It is possible to include comments in a document in the same way as in most scripting languages:

```yaml/14.yml```

Note that these comments must not (and can not) contain useful parsing information since they are not generally accessible to the parser client code.

## Multiple Documents

In the same file or stream, you can insert several YAML documents afterwards, starting them with a line consisting of three dashes (`---`) and terminating them with a line of three dots (`...`) as in the example below:

```yaml/15.yml```

Note that by default, YAML parsers wait for one document per file and may issue an error if they encounter more than one document. It is then necessary to use a particular function able to parse multiple documents (like `yaml.load_all()` for PyYaml for instance).

These documents can then be extracted sequentially from the stream.

Advanced syntax
===============

In previous section, we saw the bare minimum to get by with YAML. We will now deal with more advanced notions that are not necessary in the first place.

References
----------

YAML references are similar to pointers in programming languages. For instance:

```yaml/16.yml```

Which gives while parsed:

```python
{'Tuesday': 'potatoes', 'Saturday': 'potatoes',
 'Thursday': 'potatoes', 'Monday': 'potatoes',
 'Friday': 'potatoes', 'Sunday': 'potatoes',
 'Wednesday': 'potatoes'}
```

Note that an alias, indicated by an asterisk `*`, must point to a valid anchor, indicated by an ampersand `&`, otherwise it results in a parsing error. So the following file will cause an error during parsing:

```yaml/17.yml```

tags
----

Tags are data types indicators. By default, it is not necessary to indicate the type of data that is deduced from their form. However, in some cases, it may be necessary to force the data type, and YAML defines following default types:

```yaml/18.yml```

Matching tags begin with two exclamation points. When parsing, we get following types in Python:

```python
{'binary': 'test',
 'string': 'string',
 'seq': ['element1', 'element2'],
 'map': {'key': 'value'},
 'float': 1.2,
 'boolean': True,
 'omap': [('key', 'value')],
 None: None
 'integer': 3,
 'set': set(['element1', 'element2'])}
```

Note two additional types:

- The set: not ordered and cannot contain duplicates.
- The ordered associative array: it is an associative array whose entries are ordered.

The usefulness of tags for these default types is limited. The real power of tags lies in the ability to define your own tags for your own data types.

For instance, one could define his own type for people, with two fields: first name and last name. We must first declare the tag at the beginning of the document, then we can use it in the document, as in this example:

```yaml/19.yml```

We will see later how to use tags with Java and Python APIs to deserialize YAML structures into custom data types.

It is also possible not to declare the tag and to explain it in the document, as follows:

```yaml/20.yml```

Directives
----------

The directives give instructions to parser. There are two of them:

### TAG

As seen previously, declare a tag in the document.

### YAML

Indicates the YAML version of the document. Must be in the header of the document, as in the example below:

```yaml/21.yml```

A parser must refuse to process a document of a higher major version. For example, a parser in version `1.1` should refuse to parse a document in YAML version `2.0`. It should issue a warning if it is asked to parse a document of minor higher version, such as `1.2` for instance. It must parse without protesting all versions equal or inferior, such as `1.1` and `1.0`.

Character sets and encoding
---------------------------

A YAML parser must accept any Unicode character, except for some [special characters](http://yaml.org/spec/current.html#id2513160). These characters can be encoded in *UTF-8* (default encoding), *UTF-16* or *UTF-32*. YAML parsers are able to determine the encoding of the text by examining the first character. It is therefore **impossible** to use any other encoding in a YAML file and in particular `ISO-8859-1`.

YAML APIs
=========

We will now play with YAML APIs for Java, Python and Go.

JYaml
-----

JYaml is an OpenSource library for manipulating YAML documents in Java. The project is hosted by SourceForge and can be found at [http://jyaml.sourceforge.net/](http://jyaml.sourceforge.net/). You will find on this site [a tutorial](http://jyaml.sourceforge.net/tutorial.html) and the [API References](http://jyaml.sourceforge.net/javadoc/index.html).

### Basic Usage

JYaml performs a default mapping of YAML structures to standard Java objects: it associates a list with an instance of `java.util.ArrayList`, an associative array with an instance of `java.util.HashMap` and YAML primitive types to their Java counterpart.

Thus, to load a YAML file in a Java object, we will write the following code:

```java
Object object = Yaml.load(new File("object.yml"));
```

For instance, following file:

```yaml/22.yml```

Can be loaded into memory and displayed in the terminal with following code:

```java/1.java```

This will print in the terminal:

```java
[One, 2, {four=true, three=3.0}];
```

Conversely, you can serialize a Java object in a YAML file as follows:

```java
Yaml.dump(object, new File("dump.yml"));
```

Thus, we can serialize a Java object structure with following code:

```java/2.java```

This code will produce following file:

```yaml/23.yml```

Note that this dump is a little disappointing because some standard types of YAML (such as associative arrays and floating point numbers) are serialized to Java types (such as `java.util.HashMap` and `java.lang. Double`). Such a file will not be loaded correctly using another programming language (or even another implementation in Java).

### Advanced use

We can also work with types that are not generic and thus load instances of Java classes from YAML files.

The first solution is to indicate the type of objects with YAML tags. Thus, the following YAML file:

```yaml/24.yml```

Will it be loaded using the following classes:

```java/3.java```

And:

```java/4.java```

These classes respect the JavaBean convention, namely that they have accessors for its fields as well as an empty constructor (without arguments, implicit in Java). By loading it with the previous source, we get on the terminal:

```yaml
[Order id='test123', articles='[
  [Article id='test456', price='3.5', quantity='1'],
  [Article id='test567', price='2.0', quantity='2']
  ] '
]
```

There is another way to load these objects without the need to explicitly specify types. To do this, use the `loadType()` method and pass it the YAML file to load as well as the type of the root object of the file. So, in our case, we could write the order file as follows:

```yaml/25.yml```

And load it with following source:

```java/5.java```

This way of loading specific types is much more convenient because it does not overload the YAML file with Java types, which makes the portability between languages impossible. However, we may regret the obligation to declare the `articles` field as an `Article` array. If declared as `List <Article>`, JYaml loads the objects in the list as instances of `Map`. However, this is because the list type information is lost in the runtime and therefore JYaml cannot know the type of items in the list and therefore loads them with the default type.

### Aliases and anchors

JYaml handles aliases and anchors for YAML files. Let's consider following example:

```yaml/26.yml```

It can be loaded with following code:

```java/6.java```

And we see that the aliases and anchors have been handled correctly.

### Flow management

It is possible to serialize Java objects into a YAML stream as follows:

```java
YamlEncoder enc = new YamlEncoder(outputStream);
enc.writeObject(object1);
enc.writeObject(object2);
enc.close();
```

There is a shortcut to serialize a collection of objects in a stream as follows:

```java
Yaml.dumpStream(collection.iterator(), file);
```

On the other hand, you can deserialize Java objects from a YAML stream as follows:

```java
YamlDecoder dec = new YamlDecoder(inputStream);
try {
  while (true) {
    Object object = dec.readObject ();
    /* do something useful */
  }
} catch (EOFException e) {
  System.out.println("Finished reading stream.");
} finally {
  dec.close();
}
```

There is a shortcut to iterate on deserialized objects in a stream:

```Java
for(Object object: Yaml.loadStream(input)) {
  /* do something useful */
}
```

### Configuration file

It is possible to configure JYaml in a file, which must be named `jyaml.yml` and be in current directory where the application runs or at the root of its *CLASSPATH*. Here is an example of such a file:

```yaml/27.yml```

This configuration makes it possible in particular to configure mappings between YAML tags and Java classes. In the example above, the following mapping:

```yaml
company: com.blah.Company
```

Allows you to shorten the YAML tag to indicate the Java class used for deserialization. Thus, we can indicate the mapping for the `com.blah.Company` class by a simple `!Company` tag.

On the other hand, this file is used to list wrappers classes that deserialize particular types whose classes do not respect the JavaBeans convention. Examples can be found [in the project sources](http://jyaml.cvs.sourceforge.net/viewvc/jyaml/jyaml/src/org/ho/yaml/wrapper/).

### Other features

It should be noted that JYaml has more to offer, in particular:

- Support for YAML Spring configuration files. This allows you to write more readable and compact files than their XML counterparts. For more information, [see this page](http://jyaml.sourceforge.net/yaml4spring.html).
- A YAML format for DBUnit configuration files. See [this page](http://jyaml.sourceforge.net/yaml4dbunit.html).

However, JYaml seems to suffer from limitations, especially for parsing dates that are not always recognized as such.

PyYAML
------

PyYaml is a Python library to manage YAML files. It can be downloaded from [http://pyyaml.org/](http://pyyaml.org/) and you can find [documentation on this page](http://pyyaml.org/wiki/PyYAML).

### Installation

PyYaml can use [LibYaml](http://pyyaml.org/wiki/LibYAML) written in C and very fast or a Python implementation that does not require this library. To install the library, [download the archive](http://pyyaml.org/download/pyyaml/PyYAML-3.08.tar.gz), unzip it, go to created directory and type `python setup.py install`, or `python setup.py --wihout-libyaml install` if you don't want to use LibYaml.

### Basic Usage

To load a YAML file, whose name is passed on command line, we can proceed as follows:

```python/3.py```

The `load()` function takes a string of bytes, unicode, binary or text as parameter. Byte strings and files must be encoded in *UTF-8* or *UTF-16*. Encoding is determined by the parser by examining the Byte Order Mark (BOM), the first byte of the file. If no BOM is found, *UTF-8* is chosen.

If the string or file contains multiple documents, we can load them all using the `yaml.load_all()` function that returns an iterator. We can write:

```python
for document in yaml.load_all(documents):
    print document
```

To serialize a Python object into YAML, we can use the `yaml.dump()` function:

```python/4.py```

The `yaml.dump()` function can take a second optional parameter which must be a binary or open text file. It then writes the result of serialization to the file.

To serialize multiple Python objects into a stream, you can use my `yaml.dump_all()` function. This function takes a list or an iterator as parameter.

The dump functions take additional parameters to indicate formatting details of generated YAML. We can thus specify the number of indentation characters to use, the number of characters per line, etc. In particular, `default_flow_style` indicates whether we use stream style for lists and associative arrays. So, following code:

```python
print yaml.dump(range(5), default_flow_style=True)
```

Produces a representation using flow notation:

```yaml/28.yml```

While following code:

```python
print yaml.dump(range (5), default_flow_style=False)
```

Produces a list notation:

```yaml/29.yml```

### Serialization and deserialization of Python classes

It is possible to explicitly declare the Python type to be used to deserialize a given YAML structure using a YAML tag. For example :

```python/5.py```

Produces following output on the terminal:

```bash
Person(name='Robert', age=25)
```

Conversely, a Python class can be serialized into a YAML stream as follows:

```python/6.py```

This code produces following output:

```bash
!!python/object:__main__.Person
age: 25
name: Robert
```

We see that PyYaml serialized the Python class using the same YAML tag notation. Note that building arbitrary Python objects from unreliable sources (typically from the internet) can be dangerous. This is why PyYaml offers the `yaml.safe_load()` function which limits the building of objects to the basic types of YAML.

The notation seen above allows to deserialize YAML structures into instances of arbitrary Python classes. However, it is possible to use a simpler notation by inheriting our `Person` class from the parent `yaml.YAMLObject` as follows:

```python/7.py```

This produces on the console:

```yaml/30.yml```

Of course, we can deserialize this YAML structure by using the `yaml.load()` function. However, this notation, which has the YAML `!person` tag, is much more elegant than previous one, which indicates the qualified name of the class and not a shorter, more meaningful symbolic name.

There is a bit of magic behind all this: the `Person` class is registered as a Python type associated with the YAML `person` tag which allows this elegant notation.

There is a way to associate a regular expression with a Python class so that, for example, the notation `3d6` is associated with a call to the constructor `Dice(3, 6)`. I leave you to dig deeper [in the appropriate section of the PyYaml documentation](http://pyyaml.org/wiki/PyYAMLDocumentation#Constructorsrepresentersresvers).

Goyaml
------

[Goyaml](http://gopkg.in/yaml.v2) is a Go library for parsing YAML files to inject content into user-defined structures. This method of parsing is generalized in Go: it is identical to that of XML or JSON for example.

### Installation

To install the library in your *GOPATH*, you should type following command line:

```bash
$ go get gopkg.in/yaml.v2
```

### Definition of the structure

Suppose we want to parse the following YAML file, which represents a user:

```yaml/31.yml```

I could define following structure to represent this user:

```go
type User struct {
  Name string
  Age  int
}
```

### Parsing the file

To parse the file, you must:

- Create an empty structure.
- Read the contents of the YAML file.
- Parse the contents of the file by passing the address of the empty structure.

So to parse our user file:

```go/1.go```

This approach is very simple and addresses most YAML parsing use cases.

### Structure tags

It is possible to tag the structure in order to specify the behavior of the parser. For instance, to specify an alternative name for the field, add following tag:

```go
type User struct {
  Name string `yaml:"first_name"`
  Age  int
}
```

It indicates that the name of the field is *first_name* in YAML source file while the name in structure suggests that it should be *name*.

The general form of the tag is: `yaml:"[<key>][,<flag1>[,<flag2>]]`, where *key* is the name of the field and *flag* can have following values:

- **omitempty** indicates that the field must be omitted if field is empty.
- **flow** serializes using the inline style (lists will then be represented by `[1, 2, 3]` and the maps by `{foo: 1, bar: 2}` for example).
- **inline** injects the structure to which it is applied so that its fields are treated as if they belonged to the enclosing structure.

### Advanced usage

It seems impossible to parse an abitrary YAML file with the technique described above, but it is false. Indeed, it is possible to write following code:

```go/2.go```

Which produces following output:

```bash
$ go run generic.go user.yml
Thing: map[interface {}]interface {}{"name":"Robert", "age":25}
```

Since `interface{}` designates any type, we can parse any YAML file. You will have to introspect the result of the parsing with *reflect* package, but this is another story (much more complicated ...).

An example of implementation of this technique can be seen in [my NeON project](http://github.com/c4s4/neon/). Note that the use of the *reflect* package is to be reserved for experienced users of the Go language, otherwise they might lose their sanity.

Conclusion
==========

Now that we have a good idea of ​​what YAML is, we can compare it to similar technologies such as JSON and XML.

YAML and JSON
-------------

These two formats of textual representation of data are very close, so much so that starting from the *1.2* version of the YAML specification, any JSON document is a valid YAML document (and can therefore be parsed by a YAML parser conforming to version *1.2* of the specification).

However, YAML has a greater readability. On the other hand, it is not tied to a particular programming language (as is JSON with JavaScript).

YAML and XML
------------

These two technologies are quite different and YAML can not do everything XML can do. In particular, YAML is not suitable as a structured text format, so we could not replace XML by YAML to write DocBook for example.

On the other hand, in the field of serialization of data, YAML is much more specialized and powerful by its recognition of the usual primitive types and data structures such as lists and associative arrays.

Furthermore, YAML has a huge advantage in terms of syntax and readability, even by someone who is not familiar with the YAML specification. So, in the domain of configuration files that need to be manipulated by people who have no particular knowledge, is YAML's natural syntax a huge advantage.

YAML Usage
----------

The two main uses of YAML are:

- Configuration files. YAML allows typed configuration files and natural syntax. The Ruby on Rails configuration files are in YAML format.
- Serialization of data. It may be convenient to exchange data in YAML format as this format is independent of platform and programming language. The presence of aliases and anchors allows intelligent serializations.

I hope this YAML presentation has given you the urge to use this data format in your own applications and spread the good word around you!

Resources
=========

Here are some useful URLs for YAML:

- [YAML Homepage](http://www.yaml.org).
- [YAML 1.2 Specification](http://yaml.org/spec/1.2/spec.html).
- [YAML Reference Card](http://www.yaml.org/refcard.html).
- [JYaml Home Page](http://jyaml.sourceforge.net).
- [JYaml Tutorial](http://jyaml.sourceforge.net/tutorial.html).
- [JYaml API References](http://jyaml.sourceforge.net/javadoc/index.html).
- [PyYaml Homepage](http://www.pyyaml.org).
- [PyYaml Documentation](http://pyyaml.org/wiki/PyYAML).
- [GoYaml Homepage](http://gopkg.in/yaml.v2).
- [GoYaml Documentation](http://godoc.org/gopkg.in/yaml.v2).

*Enjoy!*
