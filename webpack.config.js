module.exports = (env, argv) => {
    const mode = argv.mode || 'production';
    return {
        mode: mode,
        entry: './src/index.js',
        output: {
            filename: `bundle.${mode}.js`
        }
    };
};
