#####A simple bookmarklet for looking up phone numbers on [Truecaller](http://www.truecaller.com) India.


1. Right click on your bookmark bar and select  'Add Page'
2. In Name box type + Truecaller
3. In URL box copy and paste the following
  ```
  javascript: (function () {function getSelectionText() {var selectedText = "";if (window.getSelection) { selectedText = window.getSelection().toString();} return selectedText};function OpenInNewTab(url) {  var win = window.open(url, '_blank');  win.focus();};var doc = document;var text = getSelectionText();if(text){var href = 'https://truecaller.com/in/' + encodeURIComponent(text);OpenInNewTab(href);}})()

  ```
4. Select phone number text in any page
5. Click on the bookmark just created and see the magic :)

The above code works for indian numbers. If you want to use any other country number, you may need to change the https://truecaller.com/in/ to something similar.
