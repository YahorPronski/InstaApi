import '../../assets/styles/components/common/teaser.scss';

const imgSizeStyleMap = {
    small: "teaser__image--small",
    large: "teaser__image--large",
};

const Teaser = ({imgSrc, imgSize, imgAlt, children, row}) => {
    const rowCss = row ? "teaser--row" : "";
    const wrapperCss = `teaser ${rowCss}`;

    const imgSizeCss = imgSizeStyleMap[imgSize] || "";
    const imgCss = `teaser__image ${imgSizeCss}`;

    return (
        <div className={wrapperCss}>
            <img className={imgCss} src={imgSrc} alt={imgAlt} />
            <span className="teaser__text">{children}</span>
        </div>
    );
};

export default Teaser;