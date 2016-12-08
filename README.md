---
title: Sync Adapters Lab
type: lab
duration: "1:25"
creator:
    name: Drew Mahrt
    city: NYC
---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Sync Adapters Lab

## Introduction

> ***Note:*** _This should be done in pairs._

In this lab, you will be setting up a sync adapter to get the latest stock prices for the companies in your stock portfolio app (the Content Provier lab) using the [MarkIt On Demand API](http://dev.markitondemand.com/MODApis/). The prices should update every minute.

In addition, stocks that are on the NASDAQ should be shown differently than the other stocks (ie making the background green).

**You will be making a new app for this lab, separate from the content providers lab.**


## Exercise

#### Requirements

- Display stock prices from your portfolio app
- Update the stock price every minute using a Sync adapter
- Store the prices after each update in a local database
- Display the NASDAQ stocks in a unique manner
- Display the last updated time on the screen

**Hint:** You can use the Content Provider from the first app to retrieve and update data for the stocks. There is no need to create a new content provider or database.

The files you need to create:
- MainActivity
- Authenticator Service
- Authenticator
- Stock object (for displaying in the RecyclerView)
- RecyclerView Adapter
- Sync Adapter
- Sync Service
- authenticator.xml
- syncadapter.xml

**Bonus:**
- Allow the user to set the sync period
- Allow the user to choose which stock exchange shows up differently (NASDAQ must be an option)

#### Starter code

Solution code from the Content-Providers lab has been given to you, including the contract you need to access its content provider. You are welcome to use your own solution code if you want to.

#### Deliverable

An app that meets the above requirements.

<img src="./screenshots/screen1.png" width="400"/>
