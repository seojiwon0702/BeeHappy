import { bhApiInstance } from "./index.js";

const bh = bhApiInstance();

async function login(user, success, fail) {
  await bh.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  bh.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await bh.get(`/user/info/${userid}`).then(success).catch(fail);
}

function signUp(user) {
  bh.post(`/user/insert`, JSON.stringify(user));
}

function updateUser(user) {
  bh.put(`/user/${user.userid}`, JSON.stringify(user));
}

function deleteUser(userid) {
  bh.delete(`/user/${userid}`);
}

function countId(userid, success, fail) {
  bh.get(`user/${userid}`).then(success).catch(fail);
}

function getUserList(success, fail) {
  bh.get("user/userlist").then(success).catch(fail);
}

export { login, findById, signUp, updateUser, countId, deleteUser, getUserList };
