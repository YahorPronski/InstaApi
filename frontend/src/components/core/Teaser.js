import '../../assets/styles/components/core/teaser.scss';

export const ImageSize = {
    DEFAULT: 0,
    SMALL: 1,
    LARGE: 2,
};

export const Direction = {
    ROW: 0,
    COLUMN: 1,
};

const imgSizeStyleMap = {
    [ImageSize.DEFAULT]: "",
    [ImageSize.SMALL]: "teaser__image--small",
    [ImageSize.LARGE]: "teaser__image--large",
};

const dirStyleMap = {
    [Direction.ROW]: "teaser--row",
    [Direction.COLUMN]: "",
};

function Teaser(props) {
    const dirCss = dirStyleMap[props.dir ? props.dir : Direction.ROW];
    const wrapperCss = `teaser ${dirCss}`;

    const imgSizeCss = imgSizeStyleMap[props.imgSize ? props.imgSize : ImageSize.DEFAULT];
    const imgCss = `teaser__image ${imgSizeCss}`;

    return (
        <div className={wrapperCss}>
            <img className={imgCss} src={props.imgSrc} alt={props.imgAlt} />
            <div className="teaser__text">{props.children}</div>
        </div>
    );
}

export default Teaser;