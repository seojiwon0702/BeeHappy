import { bhApiInstance } from "./index.js";

const bh = bhApiInstance();

function listQnA(success, fail) {
  bh.get(`/qnaboard`).then(success).catch(fail);
}

function getQnA(articleno, success, fail) {
  bh.get(`/qnaboard/${articleno}`).then(success).catch(fail);
}

function writeQnA(article) {
  return bh.post(`/qnaboard`, JSON.stringify(article));
}

function modifyQnA(article) {
  return bh.put(`/qnaboard/${article.no}`, JSON.stringify(article));
}

function deleteQnA(articleno) {
  return bh.delete(`/qnaboard/${articleno}`);
}

export { listQnA, writeQnA, getQnA, modifyQnA, deleteQnA };
