import '../../../assets/styles/components/form/items/submitbutton.scss';

const SubmitButton = ({text, onClick}) => {
    return (
        <button
            type="button"
            className="submit-button"
            onClick={onClick}>
            {text}
        </button>
    );
};

export default SubmitButton;