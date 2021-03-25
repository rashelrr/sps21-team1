// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */

// 1. create function in script.js that will be called from index.html

// 2. this function will call twitter bot servlet and pass in a haashtag in the request
// java servlet get parameter

// 3. complete servlet
// 4. make this function fill in the html

async function getBot() {
  const responseFromServer = await fetch('/twitter-bot' + new URLSearchParams({
    hashtag: '#stopasianhate',
  }));
  const textFromResponse = await responseFromServer.text();

  const botContainer = document.getElementById('bot');
  botContainer.innerText = textFromResponse;
}
