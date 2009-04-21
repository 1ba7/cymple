Overview
--------

`cymple` is the working title for the 2009 first year project of "Group B" of
the [1BA7][] module of [Trinity College Dublin][tcd]'s [Computer Science][cs]
course. The name is a recursive acronym for *Cymple: Your Musical
Past Lovingly Envisioned*.

Its purpose is to provide a graphical application capable of visualising data
parsed from a `.csv` file about the listening habits of a set of users.

`cymple` is developed using Java and [Processing][].

Components
----------

`cymple` is structured into three different components:

 *	data layer &mdash; this provides a well-defined API for the application to
	use to get useful data from the `.csv` file. It handles all the parsing of
	the `.csv` file and stores the data in an efficient way.
 * widget toolkit &mdash; this provides a set of high-level, reusable widgets
	(implemented in Processing) which are used in the application.
 * application &mdash; this uses the data layer and the widget toolkit to
	implement an application which visualises the data from the `.csv` file.

Usage
-----
`cymple` is still in the very, very early stages of development and is unlikely
to be of any use to anybody other than its developers.

[1ba7]: https://www.cs.tcd.ie/~jonesdh/1ba7.php
[tcd]: http://tcd.ie/
[cs]: http://cs.tcd.ie/
[processing]: http://processing.org
