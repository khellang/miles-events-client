module.exports = (env, argv) => {
    const mode = argv.mode || 'production';
    return {
        mode: mode,
        entry: './src/js/index.js',
        output: {
            filename: `bundle.${mode}.js`
        }
    };
};
