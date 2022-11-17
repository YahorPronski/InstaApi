import '../../assets/styles/components/core/teaser.scss';

export const ImageSizes = {
    DEFAULT: 0,
    SMALL: 1,
    LARGE: 2,
};

export const Directions = {
    ROW: 0,
    COLUMN: 1,
};

const imgSizeStyleMap = {
    [ImageSizes.DEFAULT]: "",
    [ImageSizes.SMALL]: "teaser__image--small",
    [ImageSizes.LARGE]: "teaser__image--large",
};

const dirStyleMap = {
    [Directions.ROW]: "teaser--row",
    [Directions.COLUMN]: "",
};

function Teaser(props) {
    const dirCss = dirStyleMap[props.dir ? props.dir : Directions.ROW];
    const wrapperCss = `teaser ${dirCss}`;

    const imgSizeCss = imgSizeStyleMap[props.imgSize ? props.imgSize : ImageSizes.DEFAULT];
    const imgCss = `teaser__image ${imgSizeCss}`;

    return (
        <div className={wrapperCss}>
            <img className={imgCss} src={props.imgSrc} alt={props.imgAlt} />
            <div className="teaser__text">{props.children}</div>
        </div>
    );
}

export default Teaser;