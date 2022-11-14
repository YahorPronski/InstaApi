import React from 'react';
import '../../assets/styles/components/form/entry.scss';

const styleMap = {
    "default": "text-block-default",
    "success": "text-block-success",
    "error": "text-block-error",
};

function TextBlock(props) {
    return <p className={styleMap[props.type]}>{props.text}</p>;
}

export default TextBlock;