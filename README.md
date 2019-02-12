# Trade Republic: Android coding challenge

Hi, thanks again for your application at Trade Republic. To proceed with the interview process we have prepared a short coding exercise for you, to prove your basic knowledge of the language and tools we use to develop our Android application.

In case you have any questions, feel free to reach out to us at thomas@traderepublic.com

## Task: Fetch and display streaming market data

Developing our app, we work mainly with a websocket API that provides real-time streaming market data to display the latest stock prices with millisecond latency.
You should feel comfortable developing an app to address this type of network interaction.

For the coding challenge, create a small app that can display the current price of multiple stocks in a list.
The stock is identified via its ISIN.
The ISIN number is an international standardized identifier unique to a stock or other financial instrument.
The format is e.g. `DE000BASF111`.

1. Connect to the web socket at `ws://159.89.15.214:8080/` to receive continuous updates for a certain instrument.
Feel free to use the networking library of your choice to establish the connection.

2. Subscribe to any instrument by sending a message in the the format `{"subscribe": "{isin}"}` to the websocket.
You can choose any ISIN to connect, e.g. `{"subscribe": "DE000BASF111"}` will return updates for the BASF stock.
You can unsubscribe by sending `{"unsubscribe": "{isin}"}`.
Please note: The websocket server for the coding challenge provides only rudimentary sample data and does not reflect real stock data. It also differs from our actual websocket protocol implementation.
The service is available 24/7.

3. You will receive live quotes from the web socket for the stock.
The data format is `{"isin":"DE000BASF111","price":240.32,"bid":240.31,"ask":240.33}`.
Use the `price` field to display the data.
Display the latest prices in the related RecyclerView cells.

4. Your application should be structured to subscribe to multiple instruments.
Furthermore you should provide a possibility to add new ISINs via the UI.

5. Package your changes as a git patch and send it to us at: thomas@traderepublic.com

Please note: The coding challenge should show that you feel comfortable working with Android, networking libraries and common coding standards.
We love to code and you should too.
Thus, feel free to use any third party library that you consider appropriate.
You can also decide to use Java or Kotlin as the main language for the coding challenge.
