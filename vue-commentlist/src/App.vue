<template>
  <div id="app">
    <h1>我的博客</h1>
    <comment-form @add-comment="doAdd" />
    <comment-list title="热门评论" :comments="comments" @delete-comment="doDelete" />
  </div>
</template>

<script>
  import CommentForm from "./components/comment-form.vue";
  import CommentList from "./components/comment-list.vue";

  export default {
    name: "App",
    data() {
      return {
        comments: [],
        author: "",
        body: "",
      };
    },
    methods: {
      doDelete(id) {
        axios({
          url: "http://localhost:8089/comment_list_war_exploded/comment/del?id=" + id,
          // url: "/comment/del?id=" + id,
          method: "post",
          responseType: "json",
        }).then((resp) => {
          this.loadcomment();
          alert("要删除的 id 是：" + id);
        });
      },
      doAdd(data) {
        var rsp = new URLSearchParams();
        rsp.append("author", data.author);
        rsp.append("body", data.body);
        axios({
          url: "http://localhost:8089/comment_list_war_exploded/comment/add",
          // url: "/comment/add",
          method: "post",
          responseType: "json",
          data: rsp,
        }).then((resp) => {
          this.loadcomment();
        });
      },
      loadcomment() {
        axios({
          url: "http://localhost:8089/comment_list_war_exploded/comment/list",
          // url: "/comment/list",
          responseType: "json",
        }).then((data) => {
          this.comments = data.data;
        });
      },
    },
    components: {
      CommentForm,
      CommentList,
    },
    created() {
      this.loadcomment();
    },
  };
</script>

<style>
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 30px;
  }
</style>
