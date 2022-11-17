import '../../assets/styles/components/core/teaser.scss';
import '../../assets/styles/core/flex.scss';

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
    [Directions.ROW]: "flex flex-row justify-content-between align-items-center",
    [Directions.COLUMN]: "",
};

function Teaser(props) {
    const flexClasses = dirStyleMap[props.dir ? props.dir : Directions.ROW];
    const wrapperClasses = `teaser ${flexClasses}`;

    const imageSizeClasses = imgSizeStyleMap[props.imgSize ? props.imgSize : ImageSizes.DEFAULT];
    const imageClasses = `teaser__image ${imageSizeClasses}`;

    return (
        <div className={wrapperClasses}>
            <img className={imageClasses} src={props.imgSrc} alt={props.imgAlt} />
            <div className="teaser__text">{props.children}</div>
        </div>
    );
}

export default Teaser;