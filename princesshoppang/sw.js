// PWA 오프라인 서비스 이벤트 설정 쓸지 안쓸지는 모르겠는데 일단 해둠

// install event
self.addEventListener("install", () => {
  console.log("[Service Worker] installed");
});

// activate event
self.addEventListener("activate", (e) => {
  console.log("[Service Worker] actived", e);
});

// fetch event
self.addEventListener("fetch", (e) => {
  console.log("[Service Worker] fetched resource " + e.request.url);
});