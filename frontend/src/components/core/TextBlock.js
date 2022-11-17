import '../../assets/styles/components/core/textblock.scss';

export const Types = {
    DEFAULT: 0,
    SUCCESS: 1,
    ERROR: 2,
};

const typeStyleMape = {
    [Types.DEFAULT]: "text-block-default",
    [Types.SUCCESS]: "text-block-success",
    [Types.ERROR]: "text-block-error",
};

function TextBlock(props) {
    const wrapperClass = typeStyleMape[props.type ? props.type : Types.DEFAULT];

    return <div className={wrapperClass}>{props.children}</div>;
}

export default TextBlock;