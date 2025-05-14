There is a website: [https://http.cat](https://http.cat). For each HTTP response status code, this site provides a unique cat image.

You can get the image by sending a GET request to:  
`https://http.cat/<CODE>.jpg`,  
where `<CODE>` is the status code number.

For example, for status `200` (OK), the URL would be:  
`https://http.cat/200.jpg`.

You can open this URL in a browser to view the image.  
In this task, you’ll work with the site programmatically.

You can use the standard Java HTTP tools, or use an external library like JSoup or OkHttp.

---

## Task #1 – Get the Link

Create a class called `HttpStatusChecker`. This class should have one method:

- `String getStatusImage(int code)`. It takes an HTTP status code and returns a link to the image for that code.  
  If there is no image for the given code (the site [https://http.cat](https://http.cat) returns 404), the method should throw an Exception. 

Examples:
- Calling `getStatusImage(200)` should return the string `https://http.cat/200.jpg`
- Calling `getStatusImage(10000)` should throw an exception, because the site [https://http.cat](https://http.cat) will return a 404 response.

Test your program by calling the method with different arguments.

---

## Task #2 – Download the Image

Create a class called `HttpStatusImageDownloader`. This class should have one method:

- void downloadStatusImage(int code). It takes an HTTP status code, and if there is an image for that code, downloads it and saves it in the directory where the program was started.  
If there is no image, the method should throw an Exception.

Use the `HttpStatusChecker` class from the previous task to get the image URL and to check whether the image exists.

---

## Task #3 – Create a CLI

Create a class called `HttpImageStatusCli`. This class should have one method:

- void askStatus()

When this method is called, the program should:

- Ask the user to enter an HTTP status code (e.g., `Enter HTTP status code:`)
- The user enters a status code in the console (e.g., `200`)
- The program checks whether there is an image for that code on [https://http.cat](https://http.cat). If there is, it downloads the image. If there is no image, print the message: `There is no image for HTTP status <CODE>` (replace `<CODE>` with the entered code)
- If the user enters an invalid number (e.g., `test`), print: `Please enter valid number`

Use the `HttpStatusImageDownloader` class from the previous task.