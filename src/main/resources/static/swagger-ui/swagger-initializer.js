globalThis.onload = function () {
  const ui = SwaggerUIBundle({
    url: '/v3/api-docs',
    dom_id: '#swagger-ui',
    deepLinking: true,
    presets: [
      SwaggerUIBundle.presets.apis,
      SwaggerUIStandalonePreset
    ],
    plugins: [
      createTokenAuthPlugin()
    ],
    layout: 'StandaloneLayout',
    requestInterceptor: addAuthorizationHeader
  });

  globalThis.ui = ui;
};

function createTokenAuthPlugin() {
  return {
    statePlugins: {
      spec: {
        wrapActions: {
          updateSpec: (originalAction, system) => (...args) => {
            const result = originalAction(...args);
            const token = localStorage.getItem('token');
            if (token) {
              authorizeWithToken(system, token);
            }
            return result;
          }
        }
      }
    }
  };
}

function authorizeWithToken(system, token) {
  setTimeout(() => {
    system.authActions.authorize({
      bearerAuth: {
        name: 'bearerAuth',
        schema: { type: 'http', scheme: 'bearer' },
        value: token
      }
    });
  }, 100);
}

function addAuthorizationHeader(req) {
  const token = localStorage.getItem('token');
  if (token && !req.headers.Authorization) {
    req.headers.Authorization = `Bearer ${token}`;
  }
  return req;
}
