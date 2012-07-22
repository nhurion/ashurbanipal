Ashurbanipal
============
[![Build Status](https://secure.travis-ci.org/nhurion/ashurbanipal.png)](http://travis-ci.org/nhurion/ashurbanipal)

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

What's in there?
----------------

I use this project to test all kind of frameworks and tools.

For the moment there is:
* [Travis](http://travis-ci.org/): continuous build/test hooked to gitHub. The .travis.yml file is for configuring travis;
* [JDave](http://jdave.org/): BDD tool. Used to write tests as specifications, and ensure serializable session content.
* [Selenium](http://seleniumhq.org/): Automated browser level integration tests. Just started with that.
* [Cucumber](https://github.com/cucumber/cucumber-jvm): BDD. Allow you to write test scenario in plain English, or French,... 40 languages supported.
