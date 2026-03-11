function fn() {
  var env = karate.env || 'dev';

  var config = {
    baseUrl: 'http://localhost:8088',
    timeout: 30000
  };

  return config;
}