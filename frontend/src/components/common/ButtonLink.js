import {Link} from "react-router-dom";
import "../../assets/styles/components/common/buttonlink.scss"

const ButtonLink = ({url, children}) => {
    return <Link className="button-link" to={url}>{children}</Link>;
};

export default ButtonLink;