import { aptApiInstance, bhApiInstance } from "./index.js";

const bh = bhApiInstance();
const apt = aptApiInstance();

function sidoList(success, fail) {
  bh.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  bh.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  apt.get(``, { params: params }).then(success).catch(fail);
}

export { sidoList, gugunList, houseList };
