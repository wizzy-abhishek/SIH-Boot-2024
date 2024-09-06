<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page isELIgnored="false"%>
 <!DOCTYPE html>
 <html lang="en">
 <head>
     <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>Success Page</title>
     <style>
         body {
             margin: 0;
             padding: 0;
             display: flex;
             justify-content: center;
             align-items: center;
             height: 100vh;
             background-color: rgba(51, 51, 51, 0.1);
         }

         .popup-container {
             position: relative;
             background-color: white;
             border-radius: 10px;
             box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
             padding: 30px;
             text-align: center;
             width: 400px;
         }

         .close-btn {
             position: absolute;
             top: 15px;
             right: 15px;
             font-size: 20px;
             color: #999;
             cursor: pointer;
             font-weight: bold;
         }

         .close-btn:hover {
             color: #333;
         }

         .lottie-container {
             height: 200px;
             margin-bottom: 20px;
         }

         .message h4 {
             font-size: 25px;
             color: #333;
             font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
             font-weight: 500;
         }

         .subtext {
             font-size: 20px;
             color: #838383;
             margin-top: 10px;
         }
     </style>
 </head>
 <body>
     <div class="popup-container">
         <span class="close-btn" onclick="closePopup()">×</span>
         <div class="lottie-container" id="lottie-container"></div>
         <div class="message">
             <h4>Success! Your action is completed.</h4>
         </div>
         <div class="subtext">
             Your records have been updated in the system.
         </div>
     </div>

     <script src="https://cdnjs.cloudflare.com/ajax/libs/bodymovin/5.7.13/lottie.min.js"></script>
     <script>
         bodymovin.loadAnimation({
             container: document.getElementById('lottie-container'),
             loop: false,     //aagr vo animation ko ek hi bara chalana hoga toh false kr dena loop ko
             autoplay: true,
             path: 'Animation - 1725180579827.json'
         });

         function closePopup() {
             document.querySelector('.popup-container').style.display = 'none';
         }
     </script>
 </body>
 </html>
