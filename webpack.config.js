module.exports = (env, argv) => {
    const mode = argv.mode || 'production';
    return {
        mode: mode,
        entry: './src/index.js',
        // We don't really care about size limits etc
        // because we pipe through Google Closure anyway.
        performance: { hints: false },
        output: { filename: `bundle.${mode}.js` }
    };
};
