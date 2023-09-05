import axios from 'axios'

const API = axios.create({
    // local
    baseURL: 'http://127.0.0.1:8081/',
    // server
    // baseURL: "https://t0919.p.ssafy.io/",
    headers: {
      "Content-Type": "application/json",
    },
    // withCredentials: true,
})
export default API;