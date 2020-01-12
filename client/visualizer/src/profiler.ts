// This script is loaded by speedscope in the iframe shown in the game area when the Profiler tab is visible
// It listens for messages passed via window.postMessage

function applyCSS(css: string): void {
  const style = document.createElement('style');
  style.innerHTML = css;
  document.head.appendChild(style);
}

window.addEventListener('message', (event) => {
  const data = event.data;

  switch (data.type) {
    case 'apply-css':
      applyCSS(data.payload);
      break;
  }
});
