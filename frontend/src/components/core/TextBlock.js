import '../../assets/styles/components/core/textblock.scss';

export const Type = {
    DEFAULT: 0,
    SUCCESS: 1,
    ERROR: 2,
};

const typeStyleMape = {
    [Type.DEFAULT]: "text-block-default",
    [Type.SUCCESS]: "text-block-success",
    [Type.ERROR]: "text-block-error",
};

function TextBlock(props) {
    const wrapperClass = typeStyleMape[props.type ? props.type : Type.DEFAULT];

    return <div className={wrapperClass}>{props.children}</div>;
}

export default TextBlock;