import React from 'react';
import '../../assets/styles/components/form/entry.scss';
import '../../assets/styles/components/core/textblock.scss';

export const DEFAULT = 0;
export const SUCCESS = 1;
export const ERROR = 2;

const styleMap = {
    [DEFAULT]: "text-block-default",
    [SUCCESS]: "text-block-success",
    [ERROR]: "text-block-error",
};

function TextBlock(props) {
    const style = styleMap[props.type ? props.type : DEFAULT];

    return <div className={style}>{props.children}</div>;
}

export default TextBlock;