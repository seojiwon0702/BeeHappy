import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: () => import("@/views/Home.vue"),
  },
  {
    path: "/house",
    name: "House",
    component: () => import("@/views/House.vue"),
  },
  {
    path: "/notice",
    name: "Notice",
    component: () => import("@/views/Notice.vue"),
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "NoticeList",
        component: () => import("@/components/notice/noticeList.vue"),
      },
      {
        path: "write",
        name: "NoticeWrite",
        component: () => import("@/components/notice/noticeWrite.vue"),
      },
      {
        path: "modify/:articleno",
        name: "NoticeModify",
        component: () => import("@/components/notice/noticeModify.vue"),
      },
      {
        path: "detail/:articleno",
        name: "NoticeDetail",
        component: () => import("@/components/notice/noticeDetail.vue"),
      },
    ],
  },
  {
    path: "/qna",
    name: "QnA",
    component: () => import("@/views/QuestionAndAnswer.vue"),
    redirect: "/qna/list",
    children: [
      {
        path: "list",
        name: "QnAList",
        component: () => import("@/components/qna/qnaList.vue"),
      },
      {
        path: "write",
        name: "QnAWrite",
        component: () => import("@/components/qna/qnaWrite.vue"),
      },
      {
        path: "modify/:articleno",
        name: "QnAModify",
        component: () => import("@/components/qna/qnaModify.vue"),
      },
      {
        path: "detail/:articleno",
        name: "QnADetail",
        component: () => import("@/components/qna/qnaDetail.vue"),
      },
    ],
  },
  {
    path: "/user",
    name: "UserService",
    component: () => import("@/views/User.vue"),
    children: [
      {
        path: "signin",
        name: "SignIn",
        component: () => import("@/components/user/SignIn.vue"),
      },
      {
        path: "signup",
        name: "SignUp",
        component: () => import("@/components/user/SignUp.vue"),
      },
      {
        path: "userinfo",
        name: "UserInfo",
        component: () => import("@/components/user/UserInfo.vue"),
      },
      {
        path: "manager",
        name: "Manager",
        component: () => import("@/components/user/Manager.vue"),
      },
    ],
  },
  {
    path: "/news",
    name: "News",
    component: () => import("@/views/News.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
