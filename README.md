# Java-MarketPlace

## Introduction

Java-MarketPlace is a small program written in java, aimed to mimic Amazon's behavior.  
It's mainly a project for UNICAL - User Interface Design exam. It uses an external REST server to work with: [link](https://github.com/dodaro/SimpleRESTServerMaven)

## How to use it

Clone locally both this repository and the server linked above. Run it using any Java IDE.  
Requires at least JDK 17.0.x. Anything above that should work as well.

## Bugs

There's a small bug with the server: whenever a coupon is added by an admin member,  
it cannot be deleted by a non-admin member (e.g. user after adding it to the account).
