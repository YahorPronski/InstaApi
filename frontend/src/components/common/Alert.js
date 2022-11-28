import '../../assets/styles/components/common/alert.scss';

const typeStyleMap = {
    default: "alert-default",
    success: "alert-success",
    error: "alert-error",
};

const Alert = ({type, children}) => {
    const wrapperCss = typeStyleMap[type] || typeStyleMap.default;

    return <div className={wrapperCss}>{children}</div>;
};

export default Alert;
