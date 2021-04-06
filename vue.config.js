module.exports = {
  publicPath: "./",
  transpileDependencies: ["vuetify"],
  css: {
    loaderOptions: {
      scss: {
        prependData: `@import "~@/style/_variables.scss";`,
      },
    },
  },
};
