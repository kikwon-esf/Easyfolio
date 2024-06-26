document.addEventListener("DOMContentLoaded", function () {
  const chatbotToggler = document.querySelector(".chatbot-toggler");
  const chatIcons = document.querySelectorAll("#chat-icon");
  const chatBlock = document.querySelector(".chatBlock");

  const closeBtn = document.querySelector(".close-btn");
  const outputbox = document.querySelector(".outputbox");
  const chatInput = document.querySelector(".inputbox textarea");
  const sendChatBtn = document.querySelector("#send-btn").parentNode.parentNode;
  const messageInput = document.getElementById("message");
  const textLengthDisplay = document.querySelector(".text-length");
  var imageUploadTrigger = document.getElementById("image-upload-trigger");
  var imageUploadInput = document.getElementById("image-upload");

  chatbotToggler.addEventListener("click", function () {
    chatBlock.classList.toggle("on");
    chatIcons.forEach((chatIcon) => {
      chatIcon.classList.toggle("on");
    });
  });

  // closeBtn.addEventListener("click", () =>
  //   document.body.classList.remove("on")
  // );

  let userMessage = null;
  const inputInitHeight = chatInput.scrollHeight;

  imageUploadTrigger.addEventListener("click", function () {
    imageUploadInput.click();
  });

  imageUploadInput.addEventListener("change", function () {
    var file = this.files[0];
    if (file) {
      var reader = new FileReader();
      reader.onload = function (e) {

        var previewImage = document.createElement("img");
        previewImage.src = e.target.result;
        previewImage.style.maxWidth = "100px";
        previewImage.style.height = "auto";

        var removeButton = document.createElement("button");
        removeButton.textContent = "X";
        removeButton.onclick = function () {
          previewContainer.innerHTML = "";
          imageUploadInput.value = "";
        };

        previewContainer.appendChild(previewImage);
        previewContainer.appendChild(removeButton);

        outputbox.scrollTo(0, outputbox.scrollHeight);
        sendChatBtn.classList.add("active");
      };
      reader.readAsDataURL(file);
    }
  });

  const createChatLi = (message, className) => {
    const chatLi = document.createElement("li");
    const classes = className.split(" ");
    chatLi.classList.add("chat", ...classes);
    let chatContent;
    if (className === "outgoing") {
      chatContent = `<p>${message}</p>`;
    } else if (className === "outgoing-image") {
      chatContent = `<img src="${message}" style="max-width: 50px; height: auto;">`;
    } else {
      if (message === "...") {
        chatContent = `<span class="material-symbols-outlined">smart_toy</span><p><span class="dot-animation">.</span><span class="dot-animation" style="animation-delay: 0.15s;">.</span><span class="dot-animation" style="animation-delay: 0.3s;">.</span></p>`;
      } else {
        chatContent = `<span class="material-symbols-outlined">smart_toy</span><p>${message}</p>`;
      }
    }
    chatLi.innerHTML = chatContent;
    return chatLi;
  };

  const handleChat = () => {
    userMessage = chatInput.value.trim();
    if (!userMessage && !imageUploadInput.value) return;

    chatInput.value = "";
    chatInput.style.height = `${inputInitHeight}px`;
    textLengthDisplay.textContent = `0 / 1000`;
    sendChatBtn.classList.remove("active");

    if (userMessage) {
      outputbox.appendChild(createChatLi(userMessage, "outgoing"));
    }
    if (imageUploadInput.value) {
      var file = imageUploadInput.files[0];
      var reader = new FileReader();
      reader.onload = function (e) {
        outputbox.appendChild(createChatLi(e.target.result, "outgoing-image"));
      };
      reader.readAsDataURL(file);
      previewContainer.innerHTML = "";
      imageUploadInput.value = "";
    }
    outputbox.scrollTo(0, outputbox.scrollHeight);

    const incomingChatLi = createChatLi("...", "incoming"); // Create message with "..."

    outputbox.appendChild(incomingChatLi);
    outputbox.scrollTo(0, outputbox.scrollHeight);

    $.ajax({
      url: "/api/ai/legacyPrompt",
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify({
        prompt : userMessage
      }),
      success: function (data) {
        console.log(data);

        if (data === '' || data != null) {
          incomingChatLi.querySelector("p").textContent = data;
          console.log('a');
        }
        console.log('b');
      },
      error: function (request, status, error) {
        console.log('Error fetching data:', error);
      }
    });
  };


  chatInput.addEventListener("input", () => {
    chatInput.style.height = `${chatInput.scrollHeight}px`;

    if (chatInput.value.trim() !== "") {
      sendChatBtn.classList.add("active");
    } else {
      sendChatBtn.classList.remove("active");
    }
  });

  chatInput.addEventListener("keydown", (e) => {
    if (e.key === "Enter" && !e.shiftKey && window.innerWidth > 800) {
      e.preventDefault();
      handleChat();
    }
  });

  messageInput.addEventListener("input", function () {
    const textLength = this.value.length;
    textLengthDisplay.textContent = `${textLength} / 1000`;
  });

  sendChatBtn.addEventListener("click", handleChat);
});
