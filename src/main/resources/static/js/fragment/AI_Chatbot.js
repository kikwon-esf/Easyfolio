document.addEventListener("DOMContentLoaded", function () {
  const chatbotToggler = document.querySelector(".chatbot-toggler");
  const chatIcons = document.querySelectorAll("#chat-icon");
  const chatBlock = document.querySelector(".chatBlock");

  const closeBtn = document.querySelector(".close-btn");
  const outputbox = document.querySelector(".outputbox");
  const chatInput = document.querySelector(".inputbox textarea");
  const sendChatBtn = document.querySelector("#send-btn");
  const messageInput = document.getElementById("message");
  const textLengthDisplay = document.querySelector(".text-length");

  chatbotToggler.addEventListener("click", function () {
    chatBlock.classList.toggle("on");
    chatIcons.forEach((chatIcon) => {
      chatIcon.classList.toggle("on");
    });
  });

  closeBtn.addEventListener("click", () =>
    document.body.classList.remove("on")
  );

  let userMessage = null;
  const inputInitHeight = chatInput.scrollHeight;

  const createChatLi = (message, className) => {
    const chatLi = document.createElement("li");
    const classes = className.split(" ");
    chatLi.classList.add("chat", ...classes);
    let chatContent;
  if (className === "outgoing") {
    chatContent = `<p>${message}</p>`;
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
    if (!userMessage) return;

    chatInput.value = "";
    chatInput.style.height = `${inputInitHeight}px`;
    textLengthDisplay.textContent = `0 / 1000`;
    sendChatBtn.classList.remove("active");

    outputbox.appendChild(createChatLi(userMessage, "outgoing"));
    outputbox.scrollTo(0, outputbox.scrollHeight);

    const incomingChatLi = createChatLi("...", "incoming");

    outputbox.appendChild(incomingChatLi);
    outputbox.scrollTo(0, outputbox.scrollHeight);

    $.ajax({
      url: "/AI/Chatbot/",
      type: "GET",
      data: {
        user_input: userMessage,
      },
      success: function (data) {
        if (data.message === "SUCCESS") {
          incomingChatLi.querySelector("p").textContent = data.result;
        }
      },
      error: function (request, status, error) {
        console.log("Error fetching data:", error);
      },
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
