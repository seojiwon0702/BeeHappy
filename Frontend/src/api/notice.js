import { bhApiInstance } from "./index.js";

const bh = bhApiInstance();

function listNotice(success, fail) {
  bh.get(`/noticeboard`).then(success).catch(fail);
}

function getNotice(articleno, success, fail) {
  bh.get(`/noticeboard/${articleno}`).then(success).catch(fail);
}

function writeNotice(article) {
  return bh.post(`/noticeboard`, JSON.stringify(article));
}

function modifyNotice(article) {
  return bh.put(`/noticeboard/${article.no}`, JSON.stringify(article));
}

function deleteNotice(articleno) {
  return bh.delete(`/noticeboard/${articleno}`);
}

export { listNotice, writeNotice, getNotice, modifyNotice, deleteNotice };
