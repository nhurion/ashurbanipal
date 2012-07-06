Ashurbanipal
============

This is a small application to manage my library.
Build with Vaadin, and planned to be deployed on heroku.

What kind of name is that?
--------------------------

Projects have names, finding one isn't always easy. I like history, so that's where I take inspiration for names.
[Ashurbanipal](http://en.wikipedia.org/wiki/Ashurbanipal) is the name of an ancient king
that made one of the if not the oldest known library.

Setup
-----

Launcher is configured to use memcached for session management.
Setting the environment variable DEV_MODE to true will disable the memcached session management.

This project is using travis to do continuous build. That's what the .travis.yml file is for, configuring travis.
[![Build Status](https://secure.travis-ci.org/nhurion/ashurbanipal.png)](http://travis-ci.org/nhurion/ashurbanipal)
