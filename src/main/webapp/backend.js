async function getBot() {
    const responseFromServer = await fetch('/twitter-bot?' + new URLSearchParams({
      hashtag: '#stopasianhate',
    }));
    const textFromResponse = await responseFromServer.text();
  
    const botContainer = document.getElementById('bot');
    botContainer.innerText = textFromResponse;
}